import React from 'react';
import Footer from 'gatsby-theme-carbon/src/components/Footer';

const Content = ({ buildTime }) => (
    <p>
      <h4>Contribute:</h4>
    As this implementation solution is part of the Event Driven architeture reference architecture, the contribution policies apply the same way <a href="https://github.com/ibm-cloud-architecture/refarch-kc/blob/master/CONTRIBUTING.md">here</a>.
    </p>
);

const links = {
  firstCol: [
    { linkText: 'Contributors:' },
    { href: 'https://www.linkedin.com/in/jeromeboyer/', linkText: 'Jerome Boyer' }
  ],
};

const CustomFooter = () => <Footer  Content={Content} links={links} />;

export default CustomFooter;
