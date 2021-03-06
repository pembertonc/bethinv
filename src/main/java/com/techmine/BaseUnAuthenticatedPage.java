/*
 * Copyright 2021 Cedric Pemberton.
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
package com.techmine;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResourceReference;

/**
 *
 * @author Cedric Pemberton
 */
public class BaseUnAuthenticatedPage extends WebPage {

    public BaseUnAuthenticatedPage() {
    }

    public BaseUnAuthenticatedPage(IModel<?> model) {
        super(model);
    }

    public BaseUnAuthenticatedPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        ContextRelativeResourceReference resource = new ContextRelativeResourceReference("resources/css/style.css");
        CssHeaderItem cssHeaderItem = CssHeaderItem.forReference(resource);
        response.render(cssHeaderItem);
        cssHeaderItem = CssHeaderItem.forUrl("https://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:regular,bold");
        response.render(cssHeaderItem);
    }

}
