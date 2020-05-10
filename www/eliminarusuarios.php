<?php
    
    require("conection.php");

    
    $idUsuario = urldecode($_POST['idUsuario']);
    $sql = $mysqli->query("DELETE from USUARIOS where id_usuario = '$idUsuario' ");
    print "FUNCIONA";
    

    
    
?>