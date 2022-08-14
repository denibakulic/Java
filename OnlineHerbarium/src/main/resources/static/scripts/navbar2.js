const nav = document.querySelector('.navbar')
fetch('/templates/navbar2.html')
    .then(res=>res.text())
    .then(data=>{
        nav.innerHTML=data
    })