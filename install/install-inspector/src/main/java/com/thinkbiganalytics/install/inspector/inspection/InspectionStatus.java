package com.thinkbiganalytics.install.inspector.inspection;

/*-
 * #%L
 * kylo-install-inspector
 * %%
 * Copyright (C) 2017 - 2018 ThinkBig Analytics
 * %%
 * %% Licensed under the Apache License, Version 2.0 (the "License");
 * %% you may not use this file except in compliance with the License.
 * %% You may obtain a copy of the License at
 * %%
 * %%     http://www.apache.org/licenses/LICENSE-2.0
 * %%
 * %% Unless required by applicable law or agreed to in writing, software
 * %% distributed under the License is distributed on an "AS IS" BASIS,
 * %% WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * %% See the License for the specific language governing permissions and
 * %% limitations under the License.
 * #L%
 */

public class InspectionStatus {

    private boolean valid;
    private String description;
    private String error;

    InspectionStatus(boolean isValid) {
        this.valid = isValid;
    }

    public boolean isValid() {
        return valid;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}