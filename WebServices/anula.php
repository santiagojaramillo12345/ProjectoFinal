<?php 
if (isset($_REQUEST['usr']))
{
	$usr=$_REQUEST['usr'];
	$cnx =  mysqli_connect("localhost","root","","Empresa") or die("Ha sucedido un error inexperado en la conexion de la base de datos");
	$result = mysqli_query($cnx,"select usr from usuario where usr = '$usr'");
	if (mysqli_num_rows($result)>0)
	{
		mysqli_query($cnx,"UPDATE usuario SET activo='no' WHERE usr = '$usr'");	
	}
	else
	{
		echo "Usuario No existe....";
	}
	mysqli_close($cnx);
}
else
{
	echo "Debe especificar usr, clave, nombre y correo, respectivamente";
}
?>
