package org.akala.server.config.ws;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/ws")
public class JerseyConfig extends ResourceConfig {

  public JerseyConfig() {
    registerFinder(new PackageNamesScanner(new String[] {"org.akala.server"}, true));
  }

}
