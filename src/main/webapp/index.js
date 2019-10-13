document.getElementById("getSCRUM").addEventListener("click", showSprints);
document.getElementById("getAPIDescription").addEventListener("click", showAPIDescription);
document.getElementById("clearAllPersonsButton").addEventListener("click", cleanAllPersons);
document.getElementById("getHobbies").addEventListener("click", getHobbies);
document.getElementById("cleanIdInput").addEventListener("click", cleanIdInput);
document.getElementById("clearHobbies").addEventListener("click", cleanAllHobbies)

//let allUrl = "http://idon.dk/ca2/api/person/all";
let allUrl = "http://localhost:8080/CA2/api/person/";


//const returnSpecificPersons = document.getElementById("specificPerson");
const getIdInput = document.getElementById("inputId");
document.getElementById("specificPersonButton").addEventListener("click", getPersonById);
function getPersonById() {
    var specificPerson;
    fetch(allUrl + getIdInput.value).then(res => res.json())
            .then(person => {
                specificPerson =
                        "<tr><td>" + person.id + "</td>" +
                        "<td>" + person.fName + "</td>" +
                        "<td>" + person.lName + "</td>" +
                        "<td>" + person.email + "</td></tr>";

                document.getElementById("onePerson").innerHTML =
                        "<tr>" +
                        "<th>User id</th>" +
                        "<th>User fName</th>" +
                        "<th>User lName</th>" +
                        "<th>Email</th>" +
                        "</tr>" +
                        specificPerson;
            });
}


function cleanIdInput() {
    document.getElementById("onePerson").innerHTML = "";
    document.getElementById("inputId").value = "";
}

function cleanAllHobbies() {
    document.getElementById("allHobbies").innerHTML = "";
}

function getHobbies() {
    fetch(allUrl + "allHobby").then(res => res.json())
            .then(data => {
                var hobbies = data.all.map(hobby =>
                    "<tr><td>" + hobby.id + "</td>" +
                            "<td>" + hobby.name + "</td>" +
                            "<td>" + hobby.description + "</td>")


                var hobbyList = hobbies.join("");
                document.getElementById("allHobbies").innerHTML =
                        "<tr>" +
                        "<th>hobby id</th>" +
                        "<th>hobby name</th>" +
                        "<th>hobby description</th>" +
//                        "<th>Email</th>" +
                        "</tr>" +
                        hobbyList;
            })
}



function cleanAllPersons() {
    document.getElementById("allPersons").innerHTML = "";
}
;

document.getElementById("allPersonsButton").addEventListener("click", allPersons);
function allPersons() {
    fetch(allUrl + "all").then(res => res.json())
            .then(data => {
                var persons = data.all.map(person =>
                    "<tr><td>" + person.id + "</td>" +
                            "<td>" + person.fName + "</td>" +
                            "<td>" + person.lName + "</td>" +
                            "<td>" + person.email + "</td></tr>")




                var personList = persons.join("");
                document.getElementById("allPersons").innerHTML =
                        "<tr>" +
                        "<th>Person id</th>" +
                        "<th>Person fName</th>" +
                        "<th>Person lName</th>" +
                        "<th>Email</th>" +
                        "</tr>" +
                        personList;
            })
}

document.getElementById("addPersonButton").addEventListener("click", addUser);
const fNameNewPerson = document.getElementById("fnameInput");
const lNameNewPerson = document.getElementById("lnameInput");
const emailNewPerson = document.getElementById("emailInput");
const addressIdPerson = document.getElementById("addressIdInput");
function addUser() {
    const data = { 
        fName: fNameNewPerson.value,
        lName: lNameNewPerson.value,
        email: emailNewPerson.value
    };

    const other_params = {
        headers: {"content-type": "application/json; charset=UTF-8"},
        body: newPerson,
        method: "POST",
        mode: "cors"
    };

    fetch(allUrl + "/add/" + addressIdPerson, other_params
            .then(function (response) {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Error" + response.statusText);
                }
            }).then(function (data) {
        document.getElementById("messege").innerHTML = data.encoded;
    }).catch(function (error) {
       // document.getElementById("messege").innerHTML = error.message;
       console.log(error)
    });
    return true;


//    let newPerson = {
//        method: "POST",
//        headers: {
//            'Accept': 'application/json',
//            'Content-Type': 'application/json'
//        },
//        body: JSON.stringify({
//            fName: fNameNewPerson.value,
//            lName: lNameNewPerson.value,
//            email: emailNewPerson.value
//        })
//    };

//    fetch(allUrl + "add/" + addressIdPerson, newPerson);
//    document.getElementById("fnameInput").value = "";
//    document.getElementById("lnameInput").value = "";
//    document.getElementById("addressIdInput").value = "";
//    document.getElementById("emailInput").value = "";
}

<<<<<<< HEAD
const other_params = {
    headers: {"content-type": "application/json; charset=UTF-8"},
    body: data,
    method: "POST",
    mode: "cors"
};

fetch(url, other_params)
        .then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Could not reach the API: " + response.statusText);
            }
        }).then(function (data) {
    document.getElementById("message").innerHTML = data.encoded;
}).catch(function (error) {
    document.getElementById("message").innerHTML = error.message;
});


=======
>>>>>>> 83fcc92e419c61c15834587528e26e4e305c245d
function showSprints() {
    document.getElementById("root").innerHTML =
            '<div class="container">' +
            '<h5>Sprint one (Friday 04/10 - Monday 07/10):</h5> ' +
            "<ul><li>The API description must be almost (we are using an iterative process) complete and available as a page on your deployed project.</li>" +
            "<li>The SCRUM plan for the three mini-sprints must be available as a page on your deployed project. This can either be a copy of our suggestion below, or Sprint-1 as given below and your own plan for the remaining sprints if your “product owner” agrees (red students).</li>" +
            "<li>The CI-pipeline must be setup</li>" +
            "<li>Some of the Entity Classes and the Facade(s) must be ready with supplementing tests</li></ul>" +
            '</div><div class="container"><h5>Sprint two (Tuesday 8/10 - Wednesday 9/10):</h5>' +
            "<ul><li>Most of the Entity Classes should be ready</li>" +
            "<li>Sample data should be available in the dev-database</li>" +
            "<li>Some of the endpoints (as a minimum a GET, POST and PUT) must be ready with the corresponding DTO’s and integrations tests</li>" +
            "</div><div class='container'><h5>Sprint three (Thursday 10/10- Sunday 13/10):</h5>" +
            "<ul><li>Complete the API (as much as you have time for)</li>" +
            "<li>Implement a simple SPA which as a minimum must have the ability to use some of your GET endpoints and at least one POST endpoint. Consider pages like:</li>" +
            "<ul><li>Get all persons with a given hobby</li>" +
            "<li>Get all persons living in a given city (i.e. 2800 Lyngby)</li>" +
            "<li>Get the count of people with a given hobby</li>" +
            "<li>Get a list of all zip codes in Denmark</li>" +
            "<li>Get a list of companies with more than xx employes</li>" +
            "<li>Create a Person (with hobbies, phone, address etc.)</li></ul>" +
            '<li>Complete documentation and prepare for your review presentation after the holiday</li></ul>';


}
;

function showAPIDescription() {
    document.getElementById("root").innerHTML =
            '<div class="container">' +
            '<br/>' +
            '<table class="table table-striped">' +
            '<thead>' +
            '<tr>' +
            '<th scope="col">Method</th>' +
            '<th scope="col">URL</th>' +
            '<th scope="col">Request Body (JSON)</th>' +
            '<th scope="col">Response (JSON)</th>' +
            '<th scope="col">Error (e)</th>' +
            '</tr>' +
            '</thead>' +
            '<tbody>' +
            '<tr>  ' +
            '<td>Get</td>' +
            '<td>/api/personInfo/{phone}</td>' +
            '<td></td>' +
            '<td>person</td>' +
            '<td>(e1) :{ status : 404, "msg": "No personInfo found with that phone" }</td>' +
            '</tr>' +
            '<tr>  ' +
            '<td>Post</td>' +
            '<td>/api/addPerson/</td>' +
            '<td><ul>' +
            '<p>"name" : String</p>' +
            '<p>"street" : String</p>' +
            '<p>"city" : String</p>' +
            '<p>"phone" : String</p>' +
            '<p>"hobbies" : String</p>' +
            '</ul></td>' +
            '<td><p>"id" : "Integer"' +
            '<p>"name" : String</p>' +
            '<p>"street" : String</p>' +
            '<p>"city" : String</p>' +
            '<p>"phone" : String</p>' +
            '<p>"hobbies" : String</p></td>' +
            '<td>(e2) :{ status : 400, "msg": "All input fields must be filled" }</td>' +
            '</tr>' +
            '<tr>  ' +
            '<td>Get</td>' +
            '<td>/api/allPersonsHobby/{hobby}</td>' +
            '<td></td>' +
            '<td>[person, person....]</td>' +
            '<td>:{ status : 404, "msg": "No person with that hobby found" }</td>' +
            '</tr>                    ' +
            '<tr>  ' +
            '<td>Get</td>' +
            '<td>/api/allPersonsCity/{city}</td>' +
            '<td></td>' +
            '<td>[person, person....]</td>' +
            '<td>(e1) :{ status : 404, "msg": "No person with that city found" }</td>' +
            '</tr>' +
            '<tr>  ' +
            '<td>Get</td>' +
            '<td>/api/countofPeopleHobby/{hoppy}</td>' +
            '<td></td>' +
            '<td>int</td>' +
            '<td>(e1) :{ status : 404, "msg": "No people found with that hobby" }</td>' +
            '</tr>' +
            '<tr>  ' +
            '<td>Get</td>' +
            '<td>/api/allZip/</td>' +
            '<td></td>' +
            '<td>[zip, zip, ...]</td>' +
            '<td>(e1) :{ status : 404, "msg": "No zip found" }</td>' +
            '</tr>' +
            '<tr>  ' +
            '<td>PUT</td>' +
            '<td>/api/editPerson/{id}</td>' +
            '<td>' +
            '<p>"id" : "Integer"' +
            '<p>"name" : String</p>' +
            '<p>"street" : String</p>' +
            '<p>"city" : String</p>' +
            '<p>"phone" : String</p>' +
            '<p>"hobbies" : String</p>' +
            '</td>' +
            '<td>' +
            '<p>"id" : "Integer"' +
            '<p>"name" : String</p>' +
            '<p>"street" : String</p>' +
            '<p>"city" : String</p>' +
            '<p>"phone" : String</p>' +
            '<p>"hobbies" : String</p>' +
            '</td>' +
            '<td>(e1) :{ status : 404, "msg": "No person found" }</td>' +
            '</tr>' +
            '</tbody>' +
            '</table>' +
            '</div>';

}
;

