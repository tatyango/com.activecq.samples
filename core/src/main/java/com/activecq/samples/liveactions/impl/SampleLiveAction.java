/*
 * Copyright 2012 david gonzalez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.activecq.samples.liveactions.impl;


import com.day.cq.wcm.api.WCMException;
import com.day.cq.wcm.msm.api.ActionConfig;
import com.day.cq.wcm.msm.api.LiveAction;
import com.day.cq.wcm.msm.api.LiveRelationship;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.io.JSONWriter;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.framework.Constants;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Dictionary;

/**
 * @author david
 */
@Component(
        label = "Samples - MSM LiveAction",
        description = "",
        immediate = true,
        metatype = false
)
@Properties({
        @Property(
                label = "Vendor",
                name = Constants.SERVICE_VENDOR,
                value = "ActiveCQ",
                propertyPrivate = true
        ),
        @Property(
                label = "Name",
                value = "sampleLiveAction",
                description = "LiveAction Unique Name; Referenced in Rollout Configurations",
                name = "cq.wcm.msm.action.name",
                propertyPrivate = true
        ),
        @Property(
                label = "Title",
                value = "Samples - Live Action",
                description = "Sample AdobeCQ LiveAction implementation",
                name = "cq.wcm.msm.action.title",
                propertyPrivate = true
        ),
        @Property(
                label = "Rank",
                intValue = 10,
                name = "cq.wcm.msm.action.rank",
                description = "LiveAction Rank"
        ),
        @Property(
                label = "Properties",
                value = {"enabled"},
                cardinality = Integer.MAX_VALUE,
                name = "cq.wcm.msm.action.properties",
                description = "LiveAction Properties"
        )
})
@Service
public class SampleLiveAction implements LiveAction {

    /**
     * default logger
     */
    private final Logger log = LoggerFactory.getLogger(SampleLiveAction.class);
    private int rank;
    private String name;
    private String title;
    private String[] parameterNames;

    @Override
    public void execute(Resource resource, Resource resource2, LiveRelationship liveRelationship, boolean b, boolean b2) throws WCMException {
        // TBD
    }

    @Override
    public void execute(ResourceResolver resolver, LiveRelationship relation, ActionConfig config, boolean autoSave) throws WCMException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void execute(ResourceResolver resolver, LiveRelationship relation, ActionConfig config, boolean autoSave, boolean isResetRollout) throws WCMException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String[] getPropertiesNames() {
        return this.parameterNames;
    }

    @Override
    public String getParameterName() {
        return this.name;
    }

    @Override
    public void write(JSONWriter jsonWriter) throws JSONException {
       // Deprecated
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getRank() {
        return this.rank;
    }

    /**
     * OSGi Component Methods *
     */

    @Activate
    protected void activate(ComponentContext context) {
        Dictionary<String, Object> properties = context.getProperties();

        name = PropertiesUtil.toString(properties.get("cq.wcm.msm.action.name"), "liveActionNameNotSet");
        title = PropertiesUtil.toString(properties.get("cq.wcm.msm.action.title"), "LiveAction Title Not Set");
        rank = PropertiesUtil.toInteger(properties.get("cq.wcm.msm.action.rank"), Integer.MAX_VALUE);
        parameterNames = PropertiesUtil.toStringArray(properties.get("cq.wcm.msm.action.properties"), new String[0]);
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
    }
}