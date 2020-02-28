jQuery(document).ready(function () {
    jQuery('#btnSave').click(function () {
        let categoryModal = jQuery('#categoryModal');
        let category = getDataForm();
        jQuery.ajax({
            type: 'POST',
            url: "/admin/category",
            data: category,
            contentType: false,
            processData: false,
            cache: false,
            timeout: 60000,
            success: function (data) {
                if (data.status == 200) {
                    categoryModal.modal("hide");
                    window.location.href = window.location.origin + "/admin/category";
                } else {
                    let errors = data.errors;
                    for (let i = 0; i < errors.length; i++) {
                        if (errors[i].field) {
                            let fieldId = errors[i].field;
                            jQuery("#" + fieldId + "_errors").text(errors[i].error);
                        }
                    }
                }
            }
        });
    });
    jQuery('#btnClose').click(function () {
        let categoryModal = jQuery('#categoryModal');
        categoryModal.modal('hide');
    });

    jQuery('#categoryTable').dataTable();

    jQuery('#image').change(function (e) {
        loadImage(this);

    });
    removeError("name");

});

function deleteCategory(id) {
    let deleteCategoryModal = jQuery('#deleteCategory');
    deleteCategoryModal.modal('show');
    jQuery("#deleteProductBtn").click(function () {
        //ajax xoa
        jQuery.ajax({
            type: 'DELETE',
            contentType: 'application/json',
            url: "/admin/category/" + id,
            cache: false,
            timeout: 60000,
            success: function (data) {
                if (data.status == 200) {
                    deleteCategoryModal.modal('hide');
                    window.location.href = window.location.origin + "/admin/category";
                } else {
                    jQuery("#deleteError").text("Xoa san pham khong thanh cong");
                }
            }
        });

    });
}

function getCategoryById(id) {
    jQuery.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: "/admin/category/" + id,
        cache: false,
        timeout: 60000,
        success: function (data) {
            viewDataCategory(data);
            let categoryModal = jQuery('#categoryModal');
            categoryModal.modal('show');
        }
    });
}

function editCategory(id) {
    resetAllError();
    getCategoryById(id);
    // let categoryModal = jQuery('#categoryModal');
    // categoryModal.modal('show');
    // submitForm();
}

function createCategory() {
    resetAllError();
    viewCategory("", "", null, "", 0, "", "/upload/default.png");
    let categoryModal = jQuery('#categoryModal');
    categoryModal.modal('show');
}


function viewDataCategory(category) {
    let id = category["id"]
    let name = category["name"];
    let image = category["image"];
    let imageId = category["imageId"];
    let status = category["status"];
    let nameOld = category["nameOld"];
    let image_view = category["imageUrl"];
    viewCategory(id, name, image, imageId, status, nameOld, image_view);
}

function viewCategory(id, name, image, imageId, status, nameOld, image_view) {
    jQuery("#id").val(id);
    jQuery("#name").val(name);
    jQuery("#image").val(image);
    jQuery("#imageId").val(imageId);
    jQuery("#status").val(status);
    jQuery("#nameOld").val(nameOld);
    jQuery("#image_view").attr("src", image_view);
}

function getDataForm() {
    let emptyFile = new File([], "test.png", {type: 'image/jpg'});
    let category = new FormData();
    let id = jQuery("#id").val();
    let name = jQuery("#name").val();
    let image = jQuery("#image")[0].files[0] == null ? emptyFile : jQuery("#image")[0].files[0];
    let imageId = jQuery("#imageId").val();
    let status = jQuery("#tatus").val();
    let nameOld = jQuery("#nameOld").val();
    let image_view = jQuery("#image_view").val();
    category.set("id", id == null ? 0 : id);
    category.set("name", name);
    category.set("image", image);
    category.set("imageId", imageId == null ? 0 : imageId);
    category.set("status", status == null ? 0 : status);
    category.set("nameOld", nameOld);
    category.set("imageUrl", image_view);
    return category;
}

function removeError(id) {
    let element = jQuery("#" + id);
    let error = jQuery("#" + id + "_errors");
    element.change(function () {
        error.text("");
    });
    element.keyup(function () {
        error.text("");
    });
    element.focusin(function () {
        error.text("");
    });
}

function resetAllError() {
    resetError('name');
    resetError('phone');
    resetError('address');
    resetError('email');
}

function resetError(id) {
    jQuery('#' + id + '_errors').text('');
}


