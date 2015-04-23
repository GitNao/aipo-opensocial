/*
 * Aipo is a groupware program developed by Aimluck,Inc.
 * Copyright (C) 2004-2015 Aimluck,Inc.
 * http://www.aipo.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.aipo.orm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.cayenne.DataRow;

import com.aipo.orm.Database;
import com.aipo.orm.model.portlet.EipTMessageRoom;
import com.aipo.orm.model.security.TurbineUser;
import com.aipo.orm.service.request.SearchOptions;
import com.google.inject.Inject;

/**
 *
 */
public class AipoMessageDbService implements MessageDbService {

  private final TurbineUserDbService turbineUserDbService;

  @Inject
  public AipoMessageDbService(TurbineUserDbService turbineUserDbService) {
    this.turbineUserDbService = turbineUserDbService;
  }

  /**
   * @param userId
   * @param options
   * @return
   */
  @Override
  public List<EipTMessageRoom> find(String username, SearchOptions options) {
    TurbineUser turbineUser = turbineUserDbService.findByUsername(username);
    if (turbineUser == null) {
      return new ArrayList<EipTMessageRoom>();
    }

    // MessageUtils.getRoomListと同等の処理（pageの制約はなし）に必要な変数を取得
    Integer user_id = turbineUser.getUserId();
    String keyword = options.getFilterValue();
    int limit = options.getLimit();
    boolean isMySQL = !Database.isJdbcPostgreSQL();
    boolean isSearch = (keyword != null && keyword.length() > 0);

    StringBuilder select = new StringBuilder();

    select.append("select");
    select.append(" t2.room_id, ");
    select.append(" t2.name, ");
    select.append(" t2.has_photo, ");
    select.append(" t2.photo_modified, ");
    select.append(" t4.user_id, ");
    select.append(" t4.last_name, ");
    select.append(" t4.first_name, ");
    select.append(" t4.has_photo as user_has_photo, ");
    select.append(" t4.photo_modified as user_photo_modified, ");
    select.append(" t2.auto_name, ");
    select.append(" t2.room_type, ");

    select.append(" t2.last_message, ");
    select.append(" last_update_date, ");
    select
      .append(" (select count(*) from eip_t_message_read t3 where t3.room_id = t2.room_id and t3.user_id = #bind($user_id) and t3.is_read ='F') as unread ");

    StringBuilder count = new StringBuilder();
    count.append("select count(t2.room_id) AS c ");

    StringBuilder body = new StringBuilder();
    body
      .append("  from eip_t_message_room_member t1, eip_t_message_room t2, turbine_user t4 where t1.user_id = #bind($user_id) and t1.room_id = t2.room_id and t1.target_user_id = t4.user_id ");
    if (isSearch) {
      if (isMySQL) {
        body
          .append(" and ((t2.room_type='G' and t2.name like #bind($keyword)) or (t2.room_type='O' and CONCAT(t4.last_name,t4.first_name) like #bind($keyword))) ");
      } else {
        body
          .append(" and ((t2.room_type='G' and t2.name like #bind($keyword)) or (t2.room_type='O' and (t4.last_name || t4.first_name) like #bind($keyword))) ");
      }
    }

    StringBuilder last = new StringBuilder();

    last.append(" order by t2.last_update_date desc ");
    if (limit > 0) {
      last.append(" LIMIT ");
      last.append(limit);
    }

    List<DataRow> fetchList =
      Database.sql(
        EipTMessageRoom.class,
        select.toString() + body.toString() + last.toString()).param(
        "user_id",
        Integer.valueOf(user_id)).fetchListAsDataRow();

    List<EipTMessageRoom> list = new ArrayList<EipTMessageRoom>();
    for (DataRow row : fetchList) {
      Long unread = (Long) row.get("unread");
      Integer tUserId = (Integer) row.get("user_id");
      String lastName = (String) row.get("last_name");
      String firstName = (String) row.get("first_name");
      String hasPhoto = (String) row.get("user_has_photo");
      Date photoModified = (Date) row.get("user_photo_modified");

      EipTMessageRoom object =
        Database.objectFromRowData(row, EipTMessageRoom.class);
      object.setUnreadCount(unread.intValue());
      object.setUserId(tUserId);
      object.setFirstName(firstName);
      object.setLastName(lastName);
      object.setUserHasPhoto(hasPhoto);
      if (photoModified != null) {
        object.setUserPhotoModified(photoModified.getTime());
      }
      list.add(object);
    }

    return list;

  }

}
