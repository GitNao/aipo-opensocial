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
package com.aipo.social.opensocial.spi;

import java.util.Set;
import java.util.concurrent.Future;

import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.social.opensocial.spi.CollectionOptions;
import org.apache.shindig.social.opensocial.spi.UserId;

import com.aipo.social.opensocial.model.ALMessage;
import com.aipo.social.opensocial.model.ALMessageRoom;

public interface MessageService {

  public Future<RestfulCollection<ALMessageRoom>> getRooms(UserId userId,
      CollectionOptions options, Set<String> fields, String roomId,
      SecurityToken token);

  public Future<RestfulCollection<ALMessage>> getMessages(UserId userId,
      AipoCollectionOptions options, Set<String> fields, String roomId,
      String messageId, SecurityToken token);

  public void postMessage(UserId userId, Set<String> fields, String roomId,
      String targetUserId, String message, SecurityToken token);

}
