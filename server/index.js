'use strict'

const express = require('express');

const app = express();
const bodyParser  = require('body-parser');
app.set('port', 3000);

app.listen(app.get('port'));
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

app.get('/api/users', (req, res) => {
    try {
        setInterval(() => {
            res.send({name: 'Alan', email: 'alan.etm@gmail.com', password: '123123'});
        }, 1000 * 3);
    } catch (err) {
    }
});
app.get('*', (req, res) => {
    res.send('OK');
});

module.exports = app;