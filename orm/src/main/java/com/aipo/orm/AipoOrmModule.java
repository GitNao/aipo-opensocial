/*
 * Aipo is a groupware program developed by TOWN, Inc.
 * Copyright (C) 2004-2015 TOWN, Inc.
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
package com.aipo.orm;

import com.aipo.orm.service.ActivityDbService;
import com.aipo.orm.service.AipoActivityDbService;
import com.aipo.orm.service.AipoAppDataDbService;
import com.aipo.orm.service.AipoApplicationDbService;
import com.aipo.orm.service.AipoConfigDbService;
import com.aipo.orm.service.AipoContainerConfigDbService;
import com.aipo.orm.service.AipoMessageDbService;
import com.aipo.orm.service.AipoOAuth2ClientDbService;
import com.aipo.orm.service.AipoOAuth2TokenDbService;
import com.aipo.orm.service.AipoOAuthConsumerDbService;
import com.aipo.orm.service.AipoOAuthEntryDbService;
import com.aipo.orm.service.AipoOAuthTokenDbService;
import com.aipo.orm.service.AipoTurbineGroupDbService;
import com.aipo.orm.service.AipoTurbineUserDbService;
import com.aipo.orm.service.AppDataDbService;
import com.aipo.orm.service.ApplicationDbService;
import com.aipo.orm.service.ConfigDbService;
import com.aipo.orm.service.ContainerConfigDbService;
import com.aipo.orm.service.MessageDbService;
import com.aipo.orm.service.OAuth2ClientDbService;
import com.aipo.orm.service.OAuth2TokenDbService;
import com.aipo.orm.service.OAuthConsumerDbService;
import com.aipo.orm.service.OAuthEntryDbService;
import com.aipo.orm.service.OAuthTokenDbService;
import com.aipo.orm.service.TurbineGroupDbService;
import com.aipo.orm.service.TurbineUserDbService;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 *
 */
public class AipoOrmModule extends AbstractModule {

  /**
   *
   */
  @Override
  protected void configure() {

    bind(ActivityDbService.class).to(AipoActivityDbService.class).in(
      Scopes.SINGLETON);
    bind(AppDataDbService.class).to(AipoAppDataDbService.class).in(
      Scopes.SINGLETON);
    bind(TurbineUserDbService.class).to(AipoTurbineUserDbService.class).in(
      Scopes.SINGLETON);
    bind(TurbineGroupDbService.class).to(AipoTurbineGroupDbService.class).in(
      Scopes.SINGLETON);
    bind(ConfigDbService.class).to(AipoConfigDbService.class).in(
      Scopes.SINGLETON);
    bind(ApplicationDbService.class).to(AipoApplicationDbService.class).in(
      Scopes.SINGLETON);
    bind(ContainerConfigDbService.class)
      .to(AipoContainerConfigDbService.class)
      .in(Scopes.SINGLETON);
    bind(OAuthConsumerDbService.class).to(AipoOAuthConsumerDbService.class).in(
      Scopes.SINGLETON);
    bind(OAuthTokenDbService.class).to(AipoOAuthTokenDbService.class).in(
      Scopes.SINGLETON);
    bind(OAuthEntryDbService.class).to(AipoOAuthEntryDbService.class).in(
      Scopes.SINGLETON);
    bind(OAuth2TokenDbService.class).to(AipoOAuth2TokenDbService.class).in(
      Scopes.SINGLETON);
    bind(OAuth2ClientDbService.class).to(AipoOAuth2ClientDbService.class).in(
      Scopes.SINGLETON);
    bind(MessageDbService.class).to(AipoMessageDbService.class).in(
      Scopes.SINGLETON);
  }

}
