export function login_form_body() {
    return (`<div class="form-group m-0 w-100">
                <label>
                    <input class="form-control" type="text" placeholder="Email address" name="email">
                </label>
            </div>
            <div class="form-group m-0 w-100">
                <label>
                    <input class="form-control" type="password" placeholder="Password" name="password">
                </label>
            </div>
            <button class="btn btn-primary w-100" type="submit">Sign in</button>`)
}