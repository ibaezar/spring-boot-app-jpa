//link lenguajes
document.addEventListener("DOMContentLoaded",function(event){
	var x = document.getElementById("lang");
    x.innerHTML = localStorage.getItem("lang");
});

function selected(link){
	localStorage.setItem("lang", link.innerText);
}

//Mostrar año actual
var year = new Date().getFullYear();
var date = document.getElementById("year");
date.innerHTML = year;

const showAlert = (title, text, icon) => {		
	Swal.fire({
	  title: title,
	  text: text,
	  icon: icon,
	  confirmButtonText: 'Aceptar'
	})
}

if(document.getElementById("alert")){
	const obj = document.getElementById("alert");
	if(obj.className == "success"){
		showAlert("Que bien!", obj.innerText, obj.className);		
	}else if(obj.className == "error"){
		showAlert("Oops...", obj.innerText, obj.className);
	}
}

//Mostrar archivo seleccionado en inputs tipo file
bsCustomFileInput.init()