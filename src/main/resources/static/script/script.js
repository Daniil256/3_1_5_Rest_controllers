import {scriptAdmin} from "./admin/admin.js";
import {scriptUser} from "./user.js";
import {scriptLogout} from "./logout.js";

export const page = document.querySelector("#main-page")
const loginForm = document.querySelector('.login_form')

export const routes = {
    "http://localhost:8080/user": "/pages/user.html",
    "/user": "/pages/user.html",
    "http://localhost:8080/admin": "/pages/admin.html",
    "/admin": "/pages/admin.html",
}
export const scriptPages = {
    "http://localhost:8080/user": scriptUser,
    "/user": scriptUser,
    "http://localhost:8080/admin": scriptAdmin,
    "/admin": scriptAdmin,
    "/logout": scriptLogout,
}
if (scriptPages[window.location.pathname]) {
    scriptPages[window.location.pathname]()
}
if (loginForm) {
    fetchLogin(loginForm)
}

export function disableRef() {
    document.querySelectorAll(".no_ref").forEach(el =>
        el.addEventListener("click", (event) => {
            event.preventDefault()
            window.history.pushState({}, "", event.target.href)
            handleLocation()
        }))
}

export async function handleLocation() {
    const path = window.location.pathname
    const route = routes[path]
    if (route) {
        page.innerHTML = await fetch(route).then((data) => data.text())
    } else {
        page.innerHTML = ''
    }
    scriptPages[path]()
}


export async function fetchLogin(form) {
    form.addEventListener('submit', async e => {
        e.preventDefault()
        const formData = new FormData(form)

        await fetch("/perform-login", {
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
                if (res.url === 'http://localhost:8080/fail-login') {
                    document.querySelector('#fail_msg').innerHTML = 'Неверный логин/пароль'
                } else {
                    page.innerHTML = await fetch(routes[res.url]).then((data) => data.text())
                    scriptPages[res.url]()
                }
            })
    })
}