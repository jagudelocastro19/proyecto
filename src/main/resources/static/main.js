function doLogin() {
    //
}
function mainClickAction() {
    let action = $("#action_type").val()
    let idObtained = $("#reference_id").val()
    let entity = $("#type_entity").val()
    let controller = "";
    switch (entity) {
        case "usuarios":
            controller = "usu";
            break;
        case "categorias":
            controller = "cat";
            break;
        case "roles":
            controller = "rol";
            break;
        case "productos":
            controller = "pro";
            break;
        case "archivos":
            controller = "files";
            break;
    }

    let url;
    switch (action) {
        case "buscar":
            url = "http://localhost:5000/" + controller + "/" + action + "/" + idObtained;
            break;
        case "actualizar":
            url = "http://localhost:5000/" + controller + "/" + action + "/" + idObtained;
            break;
        case "eliminar":
            url = "http://localhost:5000/" + controller + "/" + action + "/" + idObtained;
            break;
        default:
            url = "http://localhost:5000/" + controller + "/" + action + "/";
            break;
    }
    if (action == "eliminar" || action == "actualizar") {
        if (idObtained != "") {
            console.log(url)
            location.href = url;
        } else {
            alert("Por favor introduzca un id valido")
        }
    } else {
        console.log(url)
        location.href = url;
    }

}