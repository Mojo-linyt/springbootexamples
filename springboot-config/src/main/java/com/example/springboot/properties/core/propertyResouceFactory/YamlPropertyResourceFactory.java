package com.example.springboot.properties.core.propertyResouceFactory;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * <pre>
 *  YAML配置文件读取工厂类
 * </pre>
 * <p>
 * <pre>
 * @author nicky.ma
 * 修改记录
 *    修改后版本:     修改人：  修改日期: 2019/11/13 15:44  修改内容:
 * </pre>
 */
public class YamlPropertyResourceFactory implements PropertySourceFactory {

    /**
     * Create a {@link PropertySource} that wraps the given resource.
     *
     * @param name     the name of the property source
     * @param encodedResource the resource (potentially encoded) to wrap
     * @return the new {@link PropertySource} (never {@code null})
     * @throws IOException if resource resolution failed
     */
    @Override
    public PropertySource<?> createPropertySource(@Nullable String name, EncodedResource encodedResource) throws IOException {
        String resourceName = Optional.ofNullable(name).orElse(encodedResource.getResource().getFilename());
        if (resourceName.endsWith(".yml") || resourceName.endsWith(".yaml")) {
            List<PropertySource<?>> yamlSources = new YamlPropertySourceLoader().load(resourceName, encodedResource.getResource());
            return yamlSources.get(0);
        } else {
            return new PropertiesPropertySource(resourceName, new Properties());
        }
    }
}
