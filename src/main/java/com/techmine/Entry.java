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

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author Cedric Pemberton
 */
public class Entry extends WebPage {

    private Form<String> entry;
    private TextField category;
    private TextField itemName;

    private String categoryValue;
    private String itemNameValue;
    private Label output;

    private String outputValue;

    public String getOutputValue() {

        outputValue = categoryValue + " " + itemNameValue;
        if (outputValue == null) {
            outputValue = "";
        }
        return outputValue;

    }

    public Entry() {
    }

    public Entry(IModel<?> model) {
        super(model);
    }

    public Entry(PageParameters parameters) {
        super(parameters);
    }

    @Override
    public void onInitialize() {
        super.onInitialize();

        entry = new Form<>("entry") {
            @Override
            protected void onSubmit() {
                super.onSubmit();

            }

        };
        this.add(entry);
        category = new TextField("category", new PropertyModel(this, "categoryValue"));
        entry.add(category);

        itemName = new TextField("itemName", new PropertyModel(this, "itemNameValue"));
        entry.add(itemName);

        output = new Label("output", LambdaModel.of(this::getOutputValue));
        entry.add(output);

    }
}
