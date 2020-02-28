jQuery(document).ready(function () {
    //config datatable
    jQuery('#productTable').dataTable();

    jQuery('#btnSave').click(function () {
        let provideModal = jQuery('#provideModal');
        let provide = getDataForm();
        jQuery.ajax({
            type: 'POST',
            url: "/admin/provide",
            data: provide,
            contentType: false,
            processData: false,
            cache: false,
            timeout: 60000,
            success: function (data) {
                if (data.status == 200) {
                    provideModal.modal("hide");
                    window.location.href = window.location.origin + "/admin/provide";
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
        let provideModal = jQuery('#provideModal');
        provideModal.modal('hide');
    });

    //Load image
    jQuery('#image').change(function (e) {
        loadImage(this, 'image_view');
    });

    removeError("email");
    removeError("name");
    removeError("phone");
    removeError("address");

});

//Xoa
function deleteProvide(id) {
    let deleteModal = jQuery('#deleteProvide');
    deleteModal.modal('show');

    jQuery('#deleteProvideBtn').click(function () {
        jQuery.ajax({
            type: 'DELETE',
            contentType: 'application/json',
            url: "/admin/provide/" + id,
            cache: false,
            timeout: 60000,
            success: function (data) {
                if (data.status == 200) {
                    deleteModal.modal('hide');
                    window.location.href = window.location.origin + "/admin/provide";
                } else {
                    jQuery('#deleteError').text("Xóa nhà cung cấp không thành công");
                    console.log("Loi xoa nha cung cap");
                }
            }
        });
    });
}

//create
function createProvide() {
    resetAllError();
    viewProvide("", "", "", "", "", "", "", 0, "", "/upload/default.png");
    let provideModal = jQuery('#provideModal');
    provideModal.modal('show');
}

//Edit
function editProvide(id) {
    resetAllError();
    jQuery.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: "/admin/provide/" + id,
        cache: false,
        timeout: 60000,
        success: function (data) {
            viewDataProvide(data);
            let provideModal = jQuery('#provideModal');
            provideModal.modal('show');
        }
    });
}


function getDataForm() {
    let emptyFile = new File([], "test.png", {type: 'image/jpg'});
    let provide = new FormData();
    let id = jQuery("#id").val();
    let name = jQuery("#name").val();
    let email = jQuery('#email').val();
    let phone = jQuery('#phone').val();
    let address = jQuery('#address').val();
    let image = jQuery("#image")[0].files[0] == null ? emptyFile : jQuery("#image")[0].files[0];
    let imageId = jQuery("#imageId").val();
    let status = jQuery("#tatus").val();
    let nameOld = jQuery("#nameOld").val();
    let image_view = jQuery("#image_view").val();
    provide.set("id", id == null ? 0 : id);
    provide.set("name", name);
    provide.set("email", email);
    provide.set("phone", phone);
    provide.set("address", address);
    provide.set("image", image);
    provide.set("imageId", imageId == null ? 0 : imageId);
    provide.set("status", status == null ? 0 : status);
    provide.set("nameOld", nameOld);
    provide.set("imageUrl", image_view);
    return provide;
}

function viewDataProvide(provide) {
    let id = provide["id"]
    let name = provide["name"];
    let image = provide["image"];
    let imageId = provide["imageId"];
    let status = provide["status"];
    let nameOld = provide["nameOld"];
    let image_view = provide["imageUrl"];
    let email = provide["email"];
    let phone = provide["phone"];
    let address = provide["address"];
    viewProvide(id, name, email, phone, address, image, imageId, status, nameOld, image_view);
}

function viewProvide(id, name, email, phone, address, image, imageId, status, nameOld, image_view) {
    jQuery("#id").val(id);
    jQuery("#name").val(name);
    jQuery("#image").val(image);
    jQuery("#imageId").val(imageId);
    jQuery("#status").val(status);
    jQuery("#nameOld").val(nameOld);
    jQuery('#email').val(email);
    jQuery('#phone').val(phone);
    jQuery('#address').val(address);
    jQuery("#image_view").attr("src", image_view);
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

