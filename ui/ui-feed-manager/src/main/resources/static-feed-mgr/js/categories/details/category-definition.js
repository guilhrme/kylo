(function() {
    /**
     * Manages the Category Definition section of the Category Details page.
     *
     * @constructor
     * @param $scope the application model
     * @param $mdDialog the Angular Material dialog service
     * @param $mdToast the Angular Material toast service
     * @param CategoriesService the category service
     * @param StateService the URL service
     */
    function CategoryDefinitionController($scope, $mdDialog, $mdToast, CategoriesService, StateService) {
        var self = this;

        /**
         * Category data used in "edit" mode.
         * @type {CategoryModel}
         */
        self.editModel = CategoriesService.editModel;

        /**
         * Indicates if the view is in "edit" mode.
         * @type {boolean} {@code true} if in "edit" mode or {@code false} if in "normal" mode
         */
        self.isEditable = !angular.isString(CategoriesService.model.id);

        /**
         * Category data used in "normal" mode.
         * @type {CategoryModel}
         */
        self.model = CategoriesService.model;

        /**
         * Indicates if the category can be deleted.
         * @return {boolean} {@code true} if the category can be deleted, or {@code false} otherwise
         */
        self.canDelete = function() {
            return (angular.isString(self.model.id) && (!angular.isArray(self.model.relatedFeedSummaries) || self.model.relatedFeedSummaries.length === 0));
        };

        /**
         * Returns to the category list page if creating a new category.
         */
        self.onCancel = function() {
            if (!angular.isString(self.model.id)) {
                StateService.navigateToCategories();
            }
        };

        /**
         * Deletes this category.
         */
        self.onDelete = function() {
            var name = self.editModel.name;
            CategoriesService.delete(self.editModel).then(function() {
                CategoriesService.reload();
                $mdToast.show(
                    $mdToast.simple()
                        .textContent('Successfully deleted the category ' + name)
                        .hideDelay(3000)
                );
                //redirect
                StateService.navigateToCategories();
            }, function(err) {
                $mdDialog.show(
                    $mdDialog.alert()
                        .clickOutsideToClose(true)
                        .title('Unable to delete the category')
                        .textContent('Unable to delete the category ' + name + ". " + err.data.message)
                        .ariaLabel('Unable to delete the category')
                        .ok('Got it!')
                );
            });
        };

        /**
         * Switches to "edit" mode.
         */
        self.onEdit = function() {
            self.editModel = angular.copy(self.model);
        };

        /**
         * Saves the category definition.
         */
        self.onSave = function() {
            CategoriesService.save(self.editModel).then(function() {
                CategoriesService.reload();
                self.model = self.editModel;
                $mdToast.show(
                    $mdToast.simple()
                        .textContent('Saved the Category')
                        .hideDelay(3000)
                );
            });
        };

        /**
         * Shows the icon picker dialog.
         */
        self.showIconPicker = function() {
            var self = this;
            $mdDialog.show({
                controller: 'IconPickerDialog',
                templateUrl: 'js/shared/icon-picker-dialog/icon-picker-dialog.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                fullscreen: true,
                locals: {
                    iconModel: self.editModel
                }
            })
            .then(function(msg) {
                if (msg) {
                    self.editModel.icon = msg.icon;
                    self.editModel.iconColor = msg.color;
                }
            });
        };
    }

    /**
     * Creates a directive for the Category Definition section.
     *
     * @returns {Object} the directive
     */
    function thinkbigCategoryDefinition() {
        return {
            controller: "CategoryDefinitionController",
            controllerAs: "vm",
            restrict: "E",
            scope: {},
            templateUrl: "js/categories/details/category-definition.html"
        };
    }

    angular.module(MODULE_FEED_MGR).controller('CategoryDefinitionController', CategoryDefinitionController);
    angular.module(MODULE_FEED_MGR).directive('thinkbigCategoryDefinition', thinkbigCategoryDefinition);
})();
