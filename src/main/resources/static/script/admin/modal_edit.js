// export function modal_edit(user){
//     return (`
//  <div class="modal fade" id="edit_modal${user.id}" tabindex="-1" role="dialog"
//      aria-labelledby="exampleModalLabel" aria-hidden="true">
//     <div class="modal-dialog" role="document">
//         <div class="modal-content">
//             <div class="modal-header">
//                 <h5 class="modal-title">Edit user</h5>
//                 <button type="button" class="close" data-dismiss="modal"
//                         aria-label="Close">
//                     <span aria-hidden="true">&times;</span>
//                 </button>
//             </div>
//             <div class="modal-body">
//                 <div class="form w-100 bg-white">
//                     <form method="post" action="#"
//                           class="edit_form"
//                           id="form_edit${user.id}">
//                         <input type="hidden" value="${user.id}" name="id">
//                         <label class="col-form-label-sm m-0">
//                             ID
//                             <input type="text" disabled value="${user.id}"
//                                    class="form-control form-control-sm">
//                         </label>
//                         <label class="col-form-label-sm m-0">
//                             First name
//                             <input type="text" value="${user.firstname}"
//                                    name="firstname"
//                                    class="form-control form-control-sm">
//                         </label>
//                         <label class="col-form-label-sm m-0">
//                             Last name
//                             <input type="text" value="${user.lastname}"
//                                    name="lastname"
//                                    class="form-control form-control-sm">
//                         </label>
//                         <label class="col-form-label-sm m-0">
//                             Age
//                             <input type="number" value="${user.age}" name="age"
//                                    class="form-control form-control-sm">
//                         </label>
//                         <label class="col-form-label-sm m-0">
//                             Email
//                             <input type="email" value="${user.email}"
//                                    name="email"
//                                    class="form-control form-control-sm">
//                         </label>
//                         <label class="col-form-label-sm m-0">
//                             Password
//                             <input type="password" name="password"
//                                    class="form-control form-control-sm">
//                         </label>
//                         <label class="col-form-label-sm m-0">
//                             Role
//                             <select size="2" multiple
//                                     name="roles"
//                                     class="form-control form-control-sm">
//                                 <option value="1">USER</option>
//                                 <option value="2">ADMIN</option>
//                             </select>
//                         </label>
//                     </form>
//                 </div>
//             </div>
//             <div class="modal-footer">
//                 <button type="button" class="btn btn-secondary"
//                         data-dismiss="modal">Close
//                 </button>
//                 <button type="submit" class="btn btn-primary"
//                         form="form_edit${user.id}">Edit
//                 </button>
//             </div>
//         </div>
//     </div>
// </div>
//    `)
// }