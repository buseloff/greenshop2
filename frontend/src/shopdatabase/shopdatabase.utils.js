import axios from 'axios';

export const getShopDataFromDB = async () => {
  const SERVICE_URL = 'http://localhost:8080/shop_rest/shop/categories';
  return await axios.get(SERVICE_URL);
};

export const convertShopDataToMap = collection => {
  const transformedCollection = collection.data.map(item => {
    const { title, products } = item;
    return {
      routeName: encodeURI(title.toLowerCase()),
      id: item.id,
      title,
      items: products
    };
  });

  return transformedCollection.reduce((accumulator, collection) => {
    accumulator[collection.title.toLowerCase()] = collection;
    return accumulator;
  }, {});
};
