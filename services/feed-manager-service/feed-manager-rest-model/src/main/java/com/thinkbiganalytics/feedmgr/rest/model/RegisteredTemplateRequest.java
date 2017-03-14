package com.thinkbiganalytics.feedmgr.rest.model;

import java.util.List;
import java.util.Map;

/**
 * Created by sr186054 on 3/6/17.
 */
public class RegisteredTemplateRequest {

    private String templateId;
    private String templateName;
    private boolean includePropertyDescriptors;
    private boolean includeSensitiveProperties;

    private String nifiTemplateId;
    private boolean includeAllProperties;

    private boolean isFeedEdit;

    public RegisteredTemplateRequest(){

    }

    public static RegisteredTemplateRequest requestByTemplateName(String templateName){
        return new RegisteredTemplateRequest.Builder().templateName(templateName).build();
    }

    public static RegisteredTemplateRequest requestByTemplateId(String templateId){
        return new RegisteredTemplateRequest.Builder().templateId(templateId).nifiTemplateId(templateId).build();
    }

    public static RegisteredTemplateRequest requestByNiFiTemplateProperties(String nifiTemplateId, String templateName){
        return new RegisteredTemplateRequest.Builder().nifiTemplateId(nifiTemplateId).templateName(templateName).build();
    }

    public static RegisteredTemplateRequest requestForFeedCreation(String templateId, String templateName){
        return new RegisteredTemplateRequest.Builder().templateId(templateId).templateName(templateName).includeSensitiveProperties(true).includeAllProperties(true).build();
    }

    public static RegisteredTemplateRequest requestForTemplateCreation(String templateId, String templateName){
        return new RegisteredTemplateRequest.Builder().templateId(templateId).templateName(templateName).includePropertyDescriptors(true).includeSensitiveProperties(true).includeAllProperties(true).build();
    }

    public static RegisteredTemplateRequest requestForFeedRead(String templateId, String templateName){
        return new RegisteredTemplateRequest.Builder().templateId(templateId).templateName(templateName).build();
    }



    public RegisteredTemplateRequest(String templateId, String templateName, boolean includePropertyDescriptors, boolean includeSensitiveProperties, boolean includeAllProperties, String nifiTemplateId, boolean isFeedEdit) {
        this.templateId = templateId;
        this.templateName = templateName;
        this.includePropertyDescriptors = includePropertyDescriptors;
        this.includeSensitiveProperties = includeSensitiveProperties;
        this.includeAllProperties = includeAllProperties;
        this.nifiTemplateId = nifiTemplateId;
        this.isFeedEdit = isFeedEdit;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public boolean isIncludePropertyDescriptors() {
        return includePropertyDescriptors;
    }

    public void setIncludePropertyDescriptors(boolean includePropertyDescriptors) {
        this.includePropertyDescriptors = includePropertyDescriptors;
    }

    public boolean isIncludeSensitiveProperties() {
        return includeSensitiveProperties;
    }

    public void setIncludeSensitiveProperties(boolean includeSensitiveProperties) {
        this.includeSensitiveProperties = includeSensitiveProperties;
    }

    public String getNifiTemplateId() {
        return nifiTemplateId;
    }

    public void setNifiTemplateId(String nifiTemplateId) {
        this.nifiTemplateId = nifiTemplateId;
    }

    public boolean isIncludeAllProperties() {
        return includeAllProperties;
    }

    public void setIncludeAllProperties(boolean includeAllProperties) {
        this.includeAllProperties = includeAllProperties;
    }

    public static class Builder {

        private String templateId;
        private String templateName;
        private boolean includePropertyDescriptors;
        private boolean includeSensitiveProperties;
        private String nifiTemplateId;
        private boolean includeAllProperties;

        private boolean isFeedEdit;

        public Builder() {

        }

        public Builder templateId(String templateId){
        this.templateId = templateId;
        return this;
        }
        public Builder templateName(String templateName){
            this.templateName = templateName;
            return this;
        }
        public Builder includePropertyDescriptors(boolean includePropertyDescriptors){
            this.includePropertyDescriptors = includePropertyDescriptors;
            return this;
        }

        public Builder includeSensitiveProperties(boolean includeSensitiveProperties){
            this.includeSensitiveProperties = includeSensitiveProperties;
            return this;
        }

        public Builder includeAllProperties(boolean includeAllProperties){
            this.includeAllProperties = includeAllProperties;
            return this;
        }

        public Builder nifiTemplateId(String nifiTemplateId){
            this.nifiTemplateId = nifiTemplateId;
            return this;
        }

        public Builder isFeedEdit(boolean isFeedEdit){
            this.isFeedEdit = isFeedEdit;
            return this;
        }

        public RegisteredTemplateRequest build(){
            return new RegisteredTemplateRequest(templateId,templateName,includePropertyDescriptors,includeSensitiveProperties,includeAllProperties, nifiTemplateId,isFeedEdit);
        }

    }






    }
