document.getElementById("getSCRUM").addEventListener("click", showSprints);


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