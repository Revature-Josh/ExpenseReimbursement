
  async function login(){
    let uname = document.getElementById("user-name-input").value
    let pword = document.getElementById("user-pass-input").value
    let url = 'http://127.0.0.1:7000/login'
    await fetch(url, {
        method: 'POST',
        credentials: 'include',
        body: JSON.stringify({"username":uname, "password":pword})
    })
    .then((data) => {
    return data.json();
    })
    .then((response) => {
        sessionStorage.setItem("currentlyLoggedInUser", JSON.stringify(response))
        sessionStorage.setItem("userRole", response["userRole"])
        sessionStorage.setItem("userID", response["id"])
        window.location.href = "landing.html";
        console.log(sessionStorage.getItem("userID"))
    })
}

async function logout(){
    let url = 'http://127.0.0.1:7000/logout'
    await fetch(url, {
        method: 'POST',
        credentials: 'include',
    })
    .then((data) => {
    return data.json();
    })
    .then((response) => {
        sessionStorage.clear()
        window.location.href = "index.html";
        console.log('Successfully logged out')
    })
}

function checkUser(){
    if(sessionStorage.getItem("currentlyLoggedInUser") == null){
        window.location.href = "index.html"
        console.log("There is no currently logged in user")
    } else {
    }
}

function populateTable(response){
    let tbody = document.querySelector("table tbody");
    tbody.innerHTML = "";
    for(let i = 0 ; i<response.length; i++){
        let row = document.createElement("tr")
        for(let key in response[i]){
            let td = document.createElement("td")
            if(key == "reimbSubmitted" || key == "reimbResolved"){
                td.innerHTML = new Date(response[i][key]).toLocaleString()
            } else if(!response[i][key]) {
                td.innerHTML = "N/A"
            } else if (key == "statusID"){

                if (response[i][key] == 1){
                    if(sessionStorage.getItem("userRole") == 1){
                        let reimbid = row.firstChild.innerHTML
                        td.innerHTML = '<button id="approveButton" onclick="approve(' + reimbid + ')"> &#10003</button><button id="denyButton" onclick="deny(' + reimbid + ')">&#10007</button>'
                    } else {
                        td.innerHTML = "Pending"
                    }
                }
                if (response[i][key] == 2){
                    td.innerHTML = "Approved"
                }
                if (response[i][key] == 3){
                    td.innerHTML = "Denied"
                }

            } else if (key == "typeID"){
                if (response[i][key] == 1){
                    td.innerHTML = "Lodging"
                }
                if (response[i][key] == 2){
                    td.innerHTML = "Travel"
                }
                if (response[i][key] == 3){
                    td.innerHTML = "Food"
                }
                if (response[i][key] == 4){
                    td.innerHTML = "Other"
                }
            }  else {
                td.innerHTML = response[i][key]
            }
            row.appendChild(td)
        }
        tbody.appendChild(row)
    }
}

async function createManagerTable(){
    let url = "http://127.0.0.1:7000/reimb"
    await fetch(url, {
            method: 'GET',
            credentials: 'include'
        })
        .then((data) => {
            return data.json();
        })
        .then((response) => {
            populateTable(response)
        })
}

async function createEmployeeReimbTable(){
    let url = ("http://127.0.0.1:7000/author/reimb/"+ JSON.parse(sessionStorage.getItem("currentlyLoggedInUser"))["id"])
    await fetch(url, {
            method: 'GET',
            credentials: 'include'
        })
        .then((data) => {
            return data.json();
        })
        .then((response) => {
            populateTable(response)
        })
}

async function approve(id){
    let url = 'http://127.0.0.1:7000/resolve'
    await fetch(url, {
        method: 'PUT',
        credentials: 'include',
        body: JSON.stringify({"id":id, "statusID":2, "resolverID":sessionStorage.getItem("userID")})
    })
    .then((data) => {
    return data.json();
    })
    .then((response) => {
        window.location.href = "landing.html";
        console.log('Successfully approved!')
    })
}

async function deny(id){
    let url = 'http://127.0.0.1:7000/resolve'
    await fetch(url, {
        method: 'PUT',
        credentials: 'include',
        body: JSON.stringify({"id":id, "statusID":3, "resolverID":sessionStorage.getItem("userID")})
    })
    .then((data) => {
    return data.json();
    })
    .then((response) => {
        window.location.href = "landing.html";
        console.log('Successfully denied!')
    })
}
