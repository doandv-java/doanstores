function logoutConfirm() {
    jQuery('#logoutConfirm').modal('show');
    jQuery('#deleteButton').click(function () {
        //gui den api logout
        console.log("Logout");
        jQuery('#logoutConfirm').modal('hide');
    });
}
