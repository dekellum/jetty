// ========================================================================
// Copyright (c) 2010 Intalio, Inc.
// ------------------------------------------------------------------------
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// and Apache License v2.0 which accompanies this distribution.
// The Eclipse Public License is available at 
// http://www.eclipse.org/legal/epl-v10.html
// The Apache License v2.0 is available at
// http://www.opensource.org/licenses/apache2.0.php
// You may elect to redistribute this code under either of these licenses. 
// Contributors:
//    Hugues Malphettes - initial API and implementation
// ========================================================================
package org.eclipse.jetty.osgi.boot.internal.webapp;

import java.io.File;

import org.eclipse.jetty.deploy.ContextDeployer;
import org.eclipse.jetty.deploy.WebAppDeployer;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.osgi.framework.Bundle;

/**
 * Internal interface for the class that deploys a webapp on a server.
 * Used as we migrate from the single instance of the jety server to multiple jetty servers.
 */
public interface IWebBundleDeployerHelper {

	/**
	 * Deploy a new web application on the jetty server.
	 * 
	 * @param bundle
	 *            The bundle
	 * @param webappFolderPath
	 *            The path to the root of the webapp. Must be a path relative to
	 *            bundle; either an absolute path.
	 * @param contextPath
	 *            The context path. Must start with "/"
	 * @param extraClasspath
	 * @param overrideBundleInstallLocation
	 * @param webXmlPath
	 * @param defaultWebXmlPath
	 *            TODO: parameter description
	 * @return The contexthandler created and started
	 * @throws Exception
	 */
	public abstract ContextHandler registerWebapplication(Bundle bundle,
			String webappFolderPath, String contextPath, String extraClasspath,
			String overrideBundleInstallLocation, String webXmlPath,
			String defaultWebXmlPath) throws Exception;

	/**
	 * TODO: refactor this into the createContext method of OSGiAppProvider.
	 * @see WebAppDeployer#scan()
	
	 * @param contributor
	 * @param webapp
	 * @param contextPath
	 * @param extraClasspath
	 * @param bundleInstall
	 * @param webXmlPath
	 * @param defaultWebXmlPath
	 * @return The contexthandler created and started
	 * @throws Exception
	 */
	public abstract ContextHandler registerWebapplication(Bundle contributor,
			String pathInBundleToWebApp, File webapp, String contextPath,
			String extraClasspath, File bundleInstall, String webXmlPath,
			String defaultWebXmlPath) throws Exception;

	/**
	 * Stop a ContextHandler and remove it from the collection.
	 * 
	 * @see ContextDeployer#undeploy
	 * @param contextHandler
	 * @throws Exception
	 */
	public abstract void unregister(ContextHandler contextHandler)
			throws Exception;

	/**
	 * This type of registration relies on jetty's complete context xml file.
	 * Context encompasses jndi and all other things. This makes the definition
	 * of the webapp a lot more self-contained.
	 * 
	 * @param contributor
	 * @param contextFileRelativePath
	 * @param extraClasspath
	 * @param overrideBundleInstallLocation
	 * @return The contexthandler created and started
	 * @throws Exception
	 */
	public abstract ContextHandler registerContext(Bundle contributor,
			String contextFileRelativePath, String extraClasspath,
			String overrideBundleInstallLocation) throws Exception;

}