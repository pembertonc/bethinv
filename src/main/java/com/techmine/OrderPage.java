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

import com.techmine.bethInv.dto.OrderDTO;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResourceReference;

/**
 *
 * @author Cedric Pemberton
 */
public class OrderPage extends BaseUnAuthenticatedPage {

    private Form<Void> form;
    private TextField clientName;
    private TextField itemName;
    private TextField quantity;
    private AjaxFallbackButton submit;
    private FeedbackPanel feedback;

    private String clientNameValue;
    private String itemNameValue;
    private String quantityValue;
    private String selectedCategory;
    private List<String> categories;
    private DropDownChoice category;

    private String categoryValue;
    private OrderDTO order;

    public String getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getClientNameValue() {
        return clientNameValue;
    }

    public void setClientNameValue(String clientNameValue) {
        this.clientNameValue = clientNameValue;
    }

    public String getItemNameValue() {
        return itemNameValue;
    }

    public void setItemNameValue(String itemNameValue) {
        this.itemNameValue = itemNameValue;
    }

    public String getQuantityValue() {
        return quantityValue;
    }

    public void setQuantityValue(String quantityValue) {
        this.quantityValue = quantityValue;
    }

    public OrderPage() {
        order = new OrderDTO();
    }

    public OrderPage(IModel<?> model) {
        super(model);
    }

    public OrderPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize(); //To change body of generated methods, choose Tools | Templates.

        form = new Form<Void>("order") {
            @Override
            protected void onSubmit() {
                super.onSubmit(); //To change body of generated methods, choose Tools | Templates.
            }

        };
        add(form);
        clientName = (TextField) new TextField("clientName", LambdaModel.of(order::getCustomName, order::setCustomName)).setRequired(true).setLabel(Model.of("Client Name"));
        form.add(clientName);
        itemName = new TextField("itemName", LambdaModel.of(order::getItemName, order::setItemName));
        form.add(itemName);
        quantity = new TextField("quantity", LambdaModel.of(order::getQuantity, order::setQuantity));
        form.add(quantity);
        submit = new AjaxFallbackButton("submit", form) {
            @Override
            protected void onSubmit(Optional<AjaxRequestTarget> target) {
                super.onSubmit(target);
            }

            @Override
            protected void onError(Optional<AjaxRequestTarget> target) {
                super.onError(target); //To change body of generated methods, choose Tools | Templates.
                target.get().add(feedback);
            }
        };

        form.add(submit);

        feedback = (FeedbackPanel) new FeedbackPanel("feedback").setOutputMarkupId(true);
        add(feedback);

        categories = Arrays.asList("Food", "Clothing", "Cosmetics", "Miscellaneous", "Toys");
        //When displaying a non trival list for say a list of objects we need to use an implmentation IChoiceRender.
        // Wicket comes with a default implmentation of IChoiceRender call ChoiceRender.  it takes two strigs in its construction
        // the first identifies the feild to be displayed the second indiecates the feild to be used as the indiex.  in the option.
        category = new DropDownChoice("category", LambdaModel.of(order::getCategory, order::setCategory), categories);
        form.add(category);

    }

}
