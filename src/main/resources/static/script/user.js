import {handleLocation} from "./script.js";

export async function scriptUser() {
    console.log("user")
    await fetch("http://localhost:8080/user")
        .then(res => Promise.resolve(res.json())
            .then(data => render(data)))

    function render(user) {
        document.querySelector("#header_data").innerHTML =
            `<b>${user.email}</b> with roles ${user.rolesName}`
        if (user.rolesName.match(".*ADMIN.*")) {
            document.querySelector("#role_bar").innerHTML +=
                `<div>
                <a class="btn w-100 text-left text-primary no_ref" href="/admin">Admin</a>
            </div>`
        }
        document.querySelector("#user_data").innerHTML = `
<td>${user.id}</td>
<td>${user.firstname}</td>
<td>${user.lastname}</td>
<td>${user.age}</td>
<td>${user.email}</td>
<td>${user.rolesName}</td>`
    }
    document.querySelectorAll(".no_ref").forEach(el=>
        el.addEventListener("click", (event) => {
            console.log("route")
            event = event || window.event
            event.preventDefault()
            window.history.pushState({}, "", event.target.href)
            handleLocation()
        }))
}
