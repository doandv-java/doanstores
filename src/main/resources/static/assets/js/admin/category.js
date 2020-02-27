jQuery('#image').change(function (e) {
    loadImage(this);
});
jQuery('#categoryTable').dataTable();

//Xoa
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
            if (data.status == 200) {
                return data;
            } else {
                console.log('error');
                return null;
            }
        }
    });

    let categoryModal = jQuery('#categoryModal');
    categoryModal.modal('show');
    jQuery('#okBtn').click(function () {
        categoryModal.modal('hide');
    });
}

function editCategory(id) {
    let category = getCategoryById(id);

}

function createCategory() {
    let categoryModal = jQuery('#categoryModal');
    categoryModal.modal('show');
    viewDataCategory("", "", null, "", 0, "", "/upload/default.png");
    jQuery('#btnSave').click(function () {
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
                    console.log("Thanh cong");
                    categoryModal.modal("hide");
                    window.location.href = window.location.origin + "/admin/category";
                } else {
                    let errors = data.errors;
                    for (let i = 0; i < errors.length; i++) {
                        if (errors[i].field) {
                            let fieldId = errors[i].field;
                            $("#" + fieldId + "_errors").text(errors[i].message);
                        }
                    }
                }
            }
        });
    });

    function viewDataCategory(id, name, image, imageId, status, nameOld, imageUrl) {
        jQuery("#id").val(id);
        jQuery("#name").val(name);
        jQuery("#image").val(image);
        jQuery("#imageId").val(imageId);
        jQuery("#status").val(status);
        jQuery("#nameOld").val(nameOld);
        jQuery("#imageUrl").val(imageUrl);
    }

    function getDataForm() {
        let category = new FormData();
        let id = jQuery("#id").val();
        let name = jQuery("#name").val();
        let image = jQuery("#image")[0].files[0];
        let imageId = jQuery("#imageId").val();
        let status = jQuery("#tatus").val();
        let nameOld = jQuery("#nameOld").val();
        let imageUrl = jQuery("#imageUrl").val();
        category.set("id", id == null ? 0 : id);
        category.set("name", name);
        category.set("image", image);
        category.set("imageId", imageId == null ? 0 : imageId);
        category.set("status", status == null ? 0 : status);
        category.set("nameOld", nameOld);
        category.set("imageUrl", imageUrl);
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
}


