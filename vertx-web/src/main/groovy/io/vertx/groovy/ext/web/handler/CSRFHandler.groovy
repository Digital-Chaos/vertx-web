/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.groovy.ext.web.handler;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.core.json.JsonObject
import io.vertx.groovy.ext.web.RoutingContext
import io.vertx.core.Handler
/**
 * This handler adds a CSRF token to requests which mutate state. In order change the state a (XSRF-TOKEN) cookie is set
 * with a unique token, that is expected to be sent back in a (X-XSRF-TOKEN) header.
 *
 * The behavior is to check the request body header and cookie for validity.
 *
 * This Handler requires session support, thus should be added somewhere below Session and Body handlers.
*/
@CompileStatic
public class CSRFHandler implements Handler<RoutingContext> {
  private final def io.vertx.ext.web.handler.CSRFHandler delegate;
  public CSRFHandler(Object delegate) {
    this.delegate = (io.vertx.ext.web.handler.CSRFHandler) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public void handle(RoutingContext arg0) {
    ((io.vertx.core.Handler) this.delegate).handle((io.vertx.ext.web.RoutingContext)arg0.getDelegate());
  }
  /**
   * Instantiate a new CSRFHandlerImpl with a secret
   * <p>
   * <pre>
   * CSRFHandler.create("s3cr37")
   * </pre>
   * @param secret server secret to sign the token.
   * @return 
   */
  public static CSRFHandler create(String secret) {
    def ret= InternalHelper.safeCreate(io.vertx.ext.web.handler.CSRFHandler.create(secret), io.vertx.groovy.ext.web.handler.CSRFHandler.class);
    return ret;
  }
  /**
   * Set the cookie name. By default XSRF-TOKEN is used as it is the expected name by AngularJS however other frameworks
   * might use other names.
   * @param name a new name for the cookie.
   * @return fluent
   */
  public CSRFHandler setCookieName(String name) {
    this.delegate.setCookieName(name);
    return this;
  }
  /**
   * Set the header name. By default X-XSRF-TOKEN is used as it is the expected name by AngularJS however other
   * frameworks might use other names.
   * @param name a new name for the header.
   * @return fluent
   */
  public CSRFHandler setHeaderName(String name) {
    this.delegate.setHeaderName(name);
    return this;
  }
  /**
   * Should the handler give warning messages if this handler is used in other than https protocols?
   * @param nag true to nag
   * @return fluent
   */
  public CSRFHandler setNagHttps(boolean nag) {
    this.delegate.setNagHttps(nag);
    return this;
  }
  /**
   * Set the timeout for tokens generated by the handler, by default it uses the default from the session handler.
   * @param timeout token timeout
   * @return fluent
   */
  public CSRFHandler setTimeout(long timeout) {
    this.delegate.setTimeout(timeout);
    return this;
  }
}