package org.magnum.mobilecloud.video.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.geo.GeoModule;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.hateoas.mediatype.MessageResolver;
import org.springframework.hateoas.mediatype.hal.CurieProvider;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.mvc.RepresentationModelProcessorInvoker;

import java.util.Optional;

public class RestMVCConfiguration  extends RepositoryRestMvcConfiguration {
    public RestMVCConfiguration(ApplicationContext context, ObjectFactory<ConversionService> conversionService, Optional<LinkRelationProvider> relProvider, Optional<CurieProvider> curieProvider, Optional<HalConfiguration> halConfiguration, ObjectProvider<ObjectMapper> objectMapper, ObjectProvider<RepresentationModelProcessorInvoker> invoker, MessageResolver resolver, GeoModule geoModule) {
        super(context, conversionService, relProvider, curieProvider, halConfiguration, objectMapper, invoker, resolver, geoModule);
    }
}
