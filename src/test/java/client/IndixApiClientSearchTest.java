package client;

import client.impl.IndixApiClientFactory;
import exception.IndixApiException;
import exception.InternalServerException;
import httpClient.HttpClient;
import models.searchResponse.searchResult.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.Query;
import query.QueryFactory;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class IndixApiClientSearchTest {

    final static Logger logger = LoggerFactory.getLogger(IndixApiClientSearchTest.class);

    public HttpClient getMockHttpClient(String resource) throws IOException, IndixApiException {
        MockResourceHttpClient mockHttpClientInstance = new MockResourceHttpClient();
        HttpClient mockHttpClient = mockHttpClientInstance.getMockClient(resource);
        return mockHttpClient;
    }

    @Test
    public void getProductsUniversal() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("search-json-responses0/universalSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3);

            UniversalSearchResult sr = indixApiClient.getProductsUniversal(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
            assertEquals("brandText1", sr.getProducts().get(0).getStores().get("270").getOffers().get(0).getBrandText());
            assertEquals("breadCrumbs1", sr.getProducts().get(0).getStores().get("270").getOffers().get(0).getBreadCrumbs());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsOffersPremium() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("search-json-responses0/offersPremiumSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3);

            OffersSearchResult sr = indixApiClient.getProductsOffersPremium(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsOffersStandard() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("search-json-responses0/offersStandardSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withStoreId(Arrays.asList(270));

            OffersSearchResult sr = indixApiClient.getProductsOffersStandard(searchQuery);
            assertEquals(11624, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsCatalogStandard() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("search-json-responses0/catalogStandardSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3);

            CatalogStandardSearchResult sr = indixApiClient.getProductsCatalogStandard(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsCatalogPremium() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("search-json-responses0/catalogPremiumSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3);

            CatalogPremiumSearchResult sr = indixApiClient.getProductsCatalogPremium(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
            assertEquals("brandText1", sr.getProducts().get(0).getStores().get("270").getOffers().get(0).getBrandText());
            assertEquals("breadCrumbs1", sr.getProducts().get(0).getStores().get("270").getOffers().get(0).getBreadCrumbs());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsSummary() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("search-json-responses0/summarySearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3);

            SummarySearchResult sr = indixApiClient.getProductsSummary(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsCatalogPremiumShouldNotFailIfInputHasAdditionalFields() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("search-json-responses0/universalSearchResponse.json"));
        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3);

            CatalogPremiumSearchResult sr = indixApiClient.getProductsCatalogPremium(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsCatalogStandardShouldNotFailIfInputHasAdditionalFields() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("search-json-responses0/universalSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3);

            CatalogStandardSearchResult sr = indixApiClient.getProductsCatalogStandard(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsOffersPremiumShouldNotFailIfInputHasAdditionalFields() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("search-json-responses0/universalSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3);

            OffersSearchResult sr = indixApiClient.getProductsOffersPremium(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsOffersStandardShouldNotFailIfInputHasAdditionalFields() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("search-json-responses0/universalSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3);

            OffersSearchResult sr = indixApiClient.getProductsOffersStandard(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsSummaryShouldNotFailIfInputHasAdditionalFields() throws IOException, IndixApiException {

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient("123", "123",
                getMockHttpClient("search-json-responses0/universalSearchResponse.json"));

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3);

            SummarySearchResult sr = indixApiClient.getProductsSummary(searchQuery);
            assertEquals(18672, sr.getCount());
            assertEquals(10, sr.getProducts().size());
            assertEquals(0, sr.getFacets().size());
            assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }
}
