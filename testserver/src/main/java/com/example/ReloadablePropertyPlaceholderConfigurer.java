package com.example;
import java.io.IOException;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;
import org.springframework.util.StringValueResolver;


public class ReloadablePropertyPlaceholderConfigurer 
                                    extends PropertyPlaceholderConfigurer {

    private ReloadablePlaceholderResolvingStringValueResolver reloadableValueResolver;

    public void reloadProperties() throws IOException {
        Properties props = mergeProperties();
        
        this.reloadableValueResolver.refreshProperties(props);
    }


    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        this.reloadableValueResolver = new ReloadablePlaceholderResolvingStringValueResolver(props);
        StringValueResolver valueResolver = this.reloadableValueResolver;
        this.doProcessProperties(beanFactoryToProcess, valueResolver);
    }


    private class ReloadablePlaceholderResolvingStringValueResolver 
            implements StringValueResolver {

        private final PropertyPlaceholderHelper helper;
        private final ReloadablePropertyPlaceholderConfigurerResolver resolver;

        public ReloadablePlaceholderResolvingStringValueResolver(Properties props) {
            this.helper = new PropertyPlaceholderHelper(placeholderPrefix, placeholderSuffix, valueSeparator, ignoreUnresolvablePlaceholders);
            this.resolver = new ReloadablePropertyPlaceholderConfigurerResolver(props);
        }

        @Override
        public String resolveStringValue(String strVal) throws BeansException {
            String value = this.helper.replacePlaceholders(strVal, this.resolver);
            return (value.equals(nullValue) ? null : value);
        }

        private void refreshProperties(Properties props){
            this.resolver.setProps(props);
        }
    }

    private class ReloadablePropertyPlaceholderConfigurerResolver 
            implements PlaceholderResolver {

        private Properties props;
        private ReloadablePropertyPlaceholderConfigurerResolver(Properties props) {
            this.props = props;
        }

        @Override
        public String resolvePlaceholder(String placeholderName) {
            return ReloadablePropertyPlaceholderConfigurer.this.resolvePlaceholder(placeholderName, props, SYSTEM_PROPERTIES_MODE_FALLBACK);
        }

        public void setProps(Properties props) {
            this.props = props;
        }
   }
}