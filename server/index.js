'use strict'

const express = require('express');

const app = express();
const bodyParser  = require('body-parser');
app.set('port', 3000);

app.listen(app.get('port'));
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

app.isReady = () => {
	return true;
};

app.get('/api/users', (req, res) => {
    res.send({name: 'Alan', email: 'alan.etm@gmail.com', password: '123123'});
});
app.get('*', (req, res) => {
    res.send('OK');
});

console.log(app)

module.exports = app;