module.exports = {
  siteMetadata: {
    title: 'Vessel Simulator for EDA Reference Implementation',
    description: 'A vessel mouvement simulator for the IBM EDA reference implementation',
    keywords: 'gatsby,theme,carbon',
    repository: {
      baseUrl: 'https://github.com/ibm-cloud-architecture/refarch-kc-vessel-simulator',
      subDirectory: '/docs',
      branch: 'master'
    }
  },
  pathPrefix: `/refarch-kc-vessel-simulator`,
  plugins: [
    {
      resolve: 'gatsby-plugin-manifest',
      options: {
        name: 'Carbon Design Gatsby Theme',
        short_name: 'Gatsby Theme Carbon',
        start_url: '/',
        background_color: '#ffffff',
        theme_color: '#0062ff',
        display: 'browser',
      },
    },
    {
      resolve: 'gatsby-theme-carbon',
      options: {
        isSearchEnabled: true,
        titleType: 'append'
      },
    },
    {
      resolve: `gatsby-plugin-google-analytics`,
      options: {
        trackingId: "UA-149377589-3"
      }
    }
  ],
};
