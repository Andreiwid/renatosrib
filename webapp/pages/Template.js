import React from 'react';
import PropTypes from 'prop-types';
import Header from '../components/header'

class Template extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <Header/>
        <section className="sv-container-horizontal">
          <main className="main-area sv-ml--10">            
              <div >{this.props.children}</div>            
          </main>
        </section>
      </div>
    );
  }
}

Template.displayName = 'Template';

Template.propTypes = {
  children: PropTypes.any,
};

export default Template;
