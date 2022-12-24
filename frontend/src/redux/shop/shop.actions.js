import ShopActionTypes from './shop.types';
import {
  getShopDataFromDB,
  convertShopDataToMap
} from '../../shopdatabase/shopdatabase.utils';

export const fetchCollectionsStart = () => ({
  type: ShopActionTypes.FETCH_COLLECTIONS_START
});

export const fetchCollectionsSuccess = collectionsMap => ({
  type: ShopActionTypes.FETCH_COLLECTIONS_SUCCESS,
  payload: collectionsMap
});

export const fetchCollectionsFailure = errorMessage => ({
  type: ShopActionTypes.FETCH_COLLECTIONS_FAILURE,
  payload: errorMessage
});

export const fetchCollectionsStartAsync = () => {
  return dispatch => {
    dispatch(fetchCollectionsStart());
    getShopDataFromDB().then(response => {
      const collectionsMap = convertShopDataToMap(response.data);
      dispatch(fetchCollectionsSuccess(collectionsMap));
    })
      .catch(error => dispatch(fetchCollectionsFailure(error.message)));
  };
};
