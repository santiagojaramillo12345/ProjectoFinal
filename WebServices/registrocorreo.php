<?php 
if (isset($_REQUEST['usr']) && isset($_REQUEST['clave']) && isset($_REQUEST['nombre']) && isset($_REQUEST['correo']))
{
	$usr = $_REQUEST['usr'];
	$nombre = $_REQUEST['nombre'];
	$correo = $_REQUEST['correo'];
	$clave = $_REQUEST['clave'];
	$cnx =  mysqli_connect("localhost","root","","empresa") or die("Ha sucedido un error inexperado en la conexion de la base de datos");
	$result = mysqli_query($cnx,"select correo from usuario where correo = '$correo'");
	if (mysqli_num_rows($result)==0)
	{
		mysqli_query($cnx,"INSERT INTO usuario (usr,nombre,correo,clave) VALUES ('$usr','$nombre','$correo','$clave')");	
	}
	else
	{
		echo "Correo ya existe....";
	}
	mysqli_close($cnx);
}
else
{
	echo "Debe especificar usr, clave, nombre y correo, respectivamente";
}
?>
