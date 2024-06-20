import scriptAdmin from "./admin/admin.js";
import {scriptUser} from "./user.js";
import {scriptLogout} from "./logout.js";

export const LOGIN_URL = "http://localhost:8080/perform-login"

export const page = document.querySelector("#main-page")

const loginForm = document.querySelectorAll('.login_form')
const routes = {
    "http://localhost:8080/user": "/pages/user.html",
    "/user": "/pages/user.html",
    "http://localhost:8080/admin": "/pages/admin.html",
    "/admin": "/pages/admin.html",
    "http://localhost:8080/admin/delete": "/pages/admin.html",
    "/admin/delete": "/pages/admin.html",
}
const scriptPages = {
    "http://localhost:8080/user": scriptUser,
    "/user": scriptUser,
    "http://localhost:8080/admin": scriptAdmin,
    "/admin": scriptAdmin,
    "/admin/delete": scriptAdmin,
    "/logout": scriptLogout,
}


loginForm.forEach(form =>
    form.addEventListener('submit', async e => {
        e.preventDefault()
        console.log("SUBMIT")
        const formData = new FormData(form)

        await fetch(LOGIN_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                'email': formData.get('email'),
                'password': formData.get('password')
            })
        })
            .then(async (res) => {
                window.history.pushState({}, "", res.url)
             page.innerHTML = await fetch(routes[res.url]).then((data) => data.text())

                scriptPages[res.url]()
            })
    }))


export const handleLocation = async () => {
    const path = window.location.pathname
    console.log(path)
    const route = routes[path] || routes[404]
    page.innerHTML = await fetch(route).then((data) => data.text())
    scriptPages[path]()
}