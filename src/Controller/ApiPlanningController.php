<?php

namespace App\Controller;

use App\Entity\Planning;
use App\Repository\PlanningRepository;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;

class ApiPlanningController extends AbstractController
{
    /**
     * @Route("/apim/planning", name="apim_planning_index")
     */
    public function index(PlanningRepository $planningRepository)
    {
        return $this->json($planningRepository->findAll(),200,[],['groups' => 'post:read']);
    }

    /**
     * @Route("/apim/planning", name="apim_planning_add", methods={"POST"})
     */
    public function AddPlanning(Request $request, SerializerInterface $serializer,EntityManagerInterface $em)
    {
        $jsonRecu = $request->getContent();
        $planning = $serializer->deserialize($jsonRecu,Planning::class,'json');
        //$planning->setCreatedAt(new \DateTime());
        $em->persist($planning);
        $em->flush();
        dd($planning);
    }

    /**
     * @Route("/apim/planning/removePlan/{id}", name="removePlan", methods={"DELETE"})
     */
    public function removePlan($id)
    {
        $conn = $this->getDoctrine()->getManager()
            ->getConnection();
        $RAW_QUERY="Delete FROM planning WHERE id='$id' ";
        $statement=  $conn-> prepare ($RAW_QUERY);
        $statement->execute();
        $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        return new JsonResponse("deleted");

    }
    /**
     * @Route("/apim/planning/updatecons/{id}/{description}", name="updatePlan", methods={"PUT"})
     */
    public function updatePlan(Request $request,$description ,$id) :Response
    {
        $conn = $this->getDoctrine()->getManager()
            ->getConnection();
        $RAW_QUERY="UPDATE planning SET description_p='$description'  WHERE id='$id' ";
        $statement=  $conn-> prepare ($RAW_QUERY);
        $statement->execute();
        $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        return new JsonResponse("updated");

    }

    /**
     * @Route("/apim/planning/AddPlan/{personnel_id}/{nomP}/{dateDebut}/{dateFin}/{descriptionP}", name="AddPlan")
     */
    public function AddPlan(Request $request,$personnel_id,$nomP,$dateDebut,$dateFin,$descriptionP) :Response
    {
        $entityManager =$this ->getDoctrine()->getManager()->getConnection();
        $RAW_QUERY="insert into planning (personnel_id,nom_p,date_debut,date_fin,description_p) values ($personnel_id,$nomP,$dateDebut,$dateFin,$descriptionP) ";
        $statement=  $entityManager-> prepare ($RAW_QUERY);
        $statement->execute();
        $serializer = new \Symfony\Component\Serializer\Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        return new JsonResponse("Bien Ajouter");

    }
}
