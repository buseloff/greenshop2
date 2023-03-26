const INITIAL_STATE = {
  sections: [
    {
      title: 'berries',
      imageUrl: require('../../assets/img/berries/berries.jpg'),
      id: 1,
      linkUrl: 'shop/berries'
    },
    {
      title: 'vegetables',
      imageUrl: require('../../assets/img/vegetables/vegetables.jpg'),
      id: 2,
      linkUrl: 'shop/vegetables'
    },
    {
      title: 'fruits',
      imageUrl: require('../../assets/img/fruits/fruits.jpg'),
      id: 3,
      linkUrl: 'shop/fruits'
    },
    {
      title: 'cereals',
      imageUrl:require('../../assets/img/cereals/cereals.jpg'),
      size: 'large',
      id: 4,
      linkUrl: 'shop/cereals'
    },
    {
      title: 'spices',
      imageUrl: require('../../assets/img/spices/spices.jpg'),
      size: 'large',
      id: 5,
      linkUrl: 'shop/spices'
    }
  ]
};

const directoryReducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    default:
      return state;
  }
};

export default directoryReducer;
