package com.challenger;

import com.challenger.initializer.Initializer;
import com.challenger.initializer.SpringSecurityInitializer;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.annotations.ClassInheritanceHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.springframework.web.WebApplicationInitializer;

/**
 * Created by Maksym_Baziuk on 16.11.2015.
 */
public class ApplicationRunner {
    public static void main(String[] args) throws Exception{
        Server server = new Server(8080);

        WebAppContext webapp = new WebAppContext();
        webapp.setResourceBase("/");
        webapp.setContextPath("/*");
        webapp.setConfigurations(new Configuration[]{
                new WebXmlConfiguration(),
                new AnnotationConfiguration(){
                    @Override
                    public void preConfigure(WebAppContext context) throws Exception {
                        ClassInheritanceMap map = new ClassInheritanceMap();
                        map.put(WebApplicationInitializer.class.getName(), new ConcurrentHashSet<String>() {{
                            add(Initializer.class.getName());
                            add(SpringSecurityInitializer.class.getName());
                        }});
                        context.setAttribute(CLASS_INHERITANCE_MAP, map);
                        _classInheritanceHandler = new ClassInheritanceHandler(map);
                    }
                }
        });

        server.setHandler(webapp);
        server.start();
        System.out.println("Application started!");
        server.join();
    }

}
