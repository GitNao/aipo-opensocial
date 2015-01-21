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

package com.aipo.social.core.oauth2;

import org.apache.shindig.social.core.oauth2.OAuth2Client;
import org.apache.shindig.social.core.oauth2.OAuth2Code;
import org.apache.shindig.social.core.oauth2.OAuth2DataService;
import org.apache.shindig.social.core.oauth2.OAuth2DataServiceImpl;

import com.google.inject.Inject;

/**
 * @see OAuth2DataServiceImpl
 */
public class AipoOAuth2DataService implements OAuth2DataService {

  @Inject
  public AipoOAuth2DataService() throws Exception {
  }

  /**
   * @param clientId
   * @return
   */
  @Override
  public OAuth2Client getClient(String clientId) {
    return null;
  }

  /**
   * @param clientId
   * @param authCode
   * @return
   */
  @Override
  public OAuth2Code getAuthorizationCode(String clientId, String authCode) {
    return null;
  }

  /**
   * @param clientId
   * @param authCode
   */
  @Override
  public void registerAuthorizationCode(String clientId, OAuth2Code authCode) {
  }

  /**
   * @param clientId
   * @param authCode
   */
  @Override
  public void unregisterAuthorizationCode(String clientId, String authCode) {
  }

  /**
   * @param accessToken
   * @return
   */
  @Override
  public OAuth2Code getAccessToken(String accessToken) {
    return null;
  }

  /**
   * @param clientId
   * @param accessToken
   */
  @Override
  public void registerAccessToken(String clientId, OAuth2Code accessToken) {
  }

  /**
   * @param clientId
   * @param accessToken
   */
  @Override
  public void unregisterAccessToken(String clientId, String accessToken) {
  }

  /**
   * @param refreshToken
   * @return
   */
  @Override
  public OAuth2Code getRefreshToken(String refreshToken) {
    return null;
  }

  /**
   * @param clientId
   * @param refreshToken
   */
  @Override
  public void registerRefreshToken(String clientId, OAuth2Code refreshToken) {
  }

  /**
   * @param clientId
   * @param refreshToken
   */
  @Override
  public void unregisterRefreshToken(String clientId, String refreshToken) {
  }

}
