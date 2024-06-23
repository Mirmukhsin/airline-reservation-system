<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        * {
            font-family: arial, serif;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: coral;
        }
    </style>
</head>
<body>
<h1>New Notification</h1>
<h3>Hello. This is <<${flight.airportName}>> airport to inform you that a change has been made to the <<${flight.departureTime}>> flight.
    The situation after the update is as follows:</h3>
<table>
    <tr>
        <td>Airport Name</td>
        <td>Departure From</td>
        <td>Destination</td>
        <td>Tickets Number</td>
        <td>Price</td>
        <td>Departure Time</td>
        <td>Landing Time</td>
    </tr>
        <tr>
            <td>${flight.airportName}</td>
            <td>${flight.departureFrom}</td>
            <td>${flight.destination}</td>
            <td>${flight.ticketsNum}</td>
            <td>$${flight.price}</td>
            <td>${flight.departureTime}</td>
            <td>${flight.landingTime}</td>
        </tr>
</table>
</body>
</html>