/*
 * Aipo is a groupware program developed by Aimluck,Inc.
 * Copyright (C) 2004-2010 Aimluck,Inc.
 * http://aipostyle.com/
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

package com.aipo.orm.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.CayenneException;
import org.apache.cayenne.access.Transaction;
import org.apache.cayenne.conf.ServletUtil;
import org.apache.log4j.Logger;

/**
 *
 */
public class DatabaseFilter implements Filter {

  private static final Logger logger = Logger.getLogger(DatabaseFilter.class
    .getName());

  /**
   *
   */
  public void destroy() {
  }

  /**
   * @param request
   * @param response
   * @param filterChain
   * @throws IOException
   * @throws ServletException
   */
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {
    try {
      filterChain.doFilter(request, response);
    } finally {
      BaseContext.bindThreadObjectContext(null);
      Transaction threadTransaction = Transaction.getThreadTransaction();
      if (threadTransaction != null) {
        try {
          threadTransaction.rollback();
          logger.info("transaction rollback by filter");
        } catch (IllegalStateException e) {
          logger.error(e.getMessage(), e);
        } catch (SQLException e) {
          logger.error(e.getMessage(), e);
        } catch (CayenneException e) {
          logger.error(e.getMessage(), e);
        } finally {
          Transaction.bindThreadTransaction(null);
        }
      }

    }
  }

  /**
   * @param filterConfig
   * @throws ServletException
   */
  public void init(FilterConfig filterConfig) throws ServletException {
    ServletUtil.initializeSharedConfiguration(filterConfig.getServletContext());
  }

}
