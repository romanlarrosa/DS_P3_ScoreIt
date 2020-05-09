<?php
    $mysqli = new mysqli("localhost", "si", "1234", "ScoreIt");

    if($mysqli->connect_errno){
        echo("Fallo al conectar" . $mysqli->connect_errno);
    }
?>