package org.talend.designer.camel.spring.core.parsers;

import java.util.Map;

import org.apache.camel.model.FromDefinition;
import org.apache.camel.model.OptionalIdentifiedDefinition;
import org.apache.camel.model.ToDefinition;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.TypedStringValue;
import org.talend.designer.camel.spring.core.intl.XmlFileApplicationContext;

public class ActiveMQComponentParser extends AbstractComponentParser {

	public ActiveMQComponentParser(XmlFileApplicationContext appContext) {
		super(appContext);
	}

	@Override
	protected void parse(OptionalIdentifiedDefinition oid,
			Map<String, String> map) {
		String uri = null;
		if(oid instanceof FromDefinition){
			uri = ((FromDefinition)oid).getUri();
		}else if(oid instanceof ToDefinition){
			uri = ((ToDefinition)oid).getUri();
		}
		assert uri != null;
		int index = uri.indexOf(":");
		String schema = uri.substring(0, index);
		BeanDefinition beanDefinition = getBeanDefinition(schema);
		if (beanDefinition != null) {
			MutablePropertyValues propertyValues = beanDefinition
					.getPropertyValues();
			if (propertyValues != null && !propertyValues.isEmpty()) {
				PropertyValue[] values = propertyValues.getPropertyValues();
				for (PropertyValue pv : values) {
					String name = pv.getName();
					Object value = pv.getValue();
					if(value!=null){
						if(value instanceof TypedStringValue){
							map.put(name, ((TypedStringValue)value).getValue());
						}else{
							map.put(name, value.toString());
						}
					}else{
						map.put(name, "");
					}
				}
			}
		}
		if (uri != null) {
			String[] parts = uri.split(":");
			if (parts.length > 2) {
				map.put(AMQ_MESSAGE_TYPE, parts[1]);
			} else {
				map.put(AMQ_MESSAGE_TYPE, "queue");
			}
			String remain = parts[parts.length - 1];
			index = remain.indexOf("?");
			if (index == -1) {
				map.put(AMQ_MSG_DESTINATION, remain);
			} else {
				map.put(AMQ_MSG_DESTINATION, remain.substring(0, index));
			}
			if (index != -1) {
				String[] parameters = remain.substring(index + 1).split("&");
				if (parameters == null || parameters.length == 0) {
					return;
				}
				for (String s : parameters) {
					String[] kv = s.split("=");
					if (kv.length == 2) {
						map.put(kv[0], kv[1]);
					}
				}
			}
		}
	}

	@Override
	public int getType() {
		return ACTIVEMQ;
	}

}
