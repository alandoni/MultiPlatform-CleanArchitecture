'use strict'

const express = require('express');

const app = express();
const bodyParser  = require('body-parser');
app.set('port', 3000);

app.listen(app.get('port'));
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

app.get('/api/users', (req, res) => {
    res.send({name: 'Alan', email: 'alan.etm@gmail.com', password: '123123'});
});
app.post('/api/users', (req, res) => {
    res.send({name: 'Alan', email: req.body.email, password: req.body.password});
});
app.get('*', (req, res) => {
    res.send('OK');
});

module.exports = app;