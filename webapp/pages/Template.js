import React from 'react';
import PropTypes from 'prop-types';

class Template extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <section className="sv-container-horizontal">
          <main className="eag-main-alignment">
            <div className="sv-row sv-no-margins">
              <div >{this.props.children}</div>
            </div>
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
