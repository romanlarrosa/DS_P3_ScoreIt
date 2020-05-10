<?php
    
    require("conection.php");

    
    $idUsuario = urldecode($_POST['idUsuario']);
    $sql = $mysqli->query("UPDATE  USUARIOS set verificado = true where id_usuario = '$idUsuario' ");
    print "FUNCIONA";
    

    
    
?>