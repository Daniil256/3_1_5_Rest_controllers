export function modal_delete(user) {
    return (`
    <div class="modal fade" id="delete_modal${user.id}" tabindex="-1"
     role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Delete user</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form w-100 bg-white">
                    <form method="post" action="#"
                          class="w-50 mr-auto ml-auto">
                        <input type="hidden" value="${user.id}" name="id">
                        <label class="col-form-label-sm m-0">
                            ID
                            <input disabled type="text" value="${user.id}"
                                   class="form-control form-control-sm">
                        </label>
                        <label class="col-form-label-sm m-0">
                            First name
                            <input disabled type="text" value="${user.firstname}"
                                   class="form-control form-control-sm">
                        </label>
                        <label class="col-form-label-sm m-0">
                            Last name
                            <input disabled type="text" value="${user.lastname}"
                                   class="form-control form-control-sm">
                        </label>
                        <label class="col-form-label-sm m-0">
                            Age
                            <input disabled type="number" value="${user.age}"
                                   class="form-control form-control-sm">
                        </label>
                        <label class="col-form-label-sm m-0">
                            Email
                            <input disabled type="email" value="${user.email}"
                                   class="form-control form-control-sm">
                        </label>
                       <label class="col-form-label-sm m-0" >
                            Role
                            <select size="2" multiple 
                                    name="roles" disabled
                                    class="form-control form-control-sm">     
                                    ${user.roles.map(role =>
        `<option value="${role.id}">${role.name.replaceAll("ROLE_", "")}</option>`
    )}       
                            </select>
                        </label>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close
                </button>
                <a class="btn btn-danger no_ref" id="delete_user_btn"
                   href="admin/delete?id=${user.id}">Delete</a>
            </div>
        </div>
    </div>
</div>`)
}