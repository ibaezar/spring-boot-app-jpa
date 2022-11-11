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