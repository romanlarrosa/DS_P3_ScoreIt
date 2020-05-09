<?php
    
    require("conection.php");

    $usuario = mysqli_real_escape_string($mysqli, $_POST['user']);
    $pass = $_POST['pass'];
    $tipo = $_POST['tipo'];


    //Verificamos las variables
    if(empty($usuario)|| empty($pass) || empty($tipo)){
        echo "ERR1";
    }
    else{
        //$_user = mysql_real_escape_string($usuario);
        $pass = password_hash($pass, PASSWORD_DEFAULT);
        $sql = "INSERT into USUARIOS (nombre, pass, tipo) values ('$usuario', '$pass', '$tipo')";
        $query = $mysqli->query($sql);
        if($query === TRUE){
            echo "0";
        }
        else{
            echo "-1";
        }
    }
    
?>