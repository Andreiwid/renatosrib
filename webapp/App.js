import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, IndexRoute } from 'react-router';
import createHashHistory from 'history/createHashHistory';
import Template from './pages/Template';
import Home from './pages/home';

const hashHistory = createHashHistory();

ReactDOM.render(
  <Router history={hashHistory}>
    <Template>
      <Route path="/" component={Home} />
    </Template>
  </Router>,
  document.getElementById('app')
);
